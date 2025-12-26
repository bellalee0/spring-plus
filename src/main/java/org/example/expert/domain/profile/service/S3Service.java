package org.example.expert.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public String upload(MultipartFile image, String fileName) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .contentLength(image.getSize())
            .contentType(image.getContentType())
            .build();

        try {
            s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(image.getInputStream(), image.getSize())
            );
            return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
        } catch (Exception e) {
            throw new ServerException("파일 업로드 중 오류가 발생했습니다.");
        }
    }
}
