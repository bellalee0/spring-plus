package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.expert.domain.log.entity.Log;
import org.example.expert.domain.log.service.LogService;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ManagerRegisterLoggingAspect {

    private final HttpServletRequest request;
    private final LogService logService;

    @Around("execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))")
    public Object logSaveManager(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result;
        boolean success = true;

        Long loginUserId = Long.parseLong(request.getAttribute("userId").toString());

        ManagerSaveRequest requestDto = (ManagerSaveRequest) joinPoint.getArgs()[2];
        Long targetUserId = requestDto.getManagerUserId();

        String[] requestURI = request.getRequestURI().split("/");
        Long todoId = Long.parseLong(requestURI[requestURI.length - 2]);

        String description = "매니저 등록 성공";

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            success = false;
            description = throwable.getMessage();
            throw throwable;
        } finally {
            Log log = new Log(loginUserId, targetUserId, todoId, success, description);
            logService.save(log);
        }

        return result;
    }
}
