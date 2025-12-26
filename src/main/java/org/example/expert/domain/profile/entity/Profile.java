package org.example.expert.domain.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.user.entity.User;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "profiles")
@SQLRestriction("is_deleted = 0")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String imageUrl;

    @ColumnDefault("0")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    public Profile(User user, String imageUrl) {
        this.user = user;
        this.imageUrl = imageUrl;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
