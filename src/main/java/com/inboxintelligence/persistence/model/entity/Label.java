package com.inboxintelligence.persistence.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Table(
        name = "label",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_label_mailbox_full_name",
                        columnNames = {"fk_gmail_mailbox_id", "full_name"}
                )
        },
        indexes = {
                @Index(name = "idx_label_mailbox", columnList = "fk_gmail_mailbox_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_gmail_mailbox_id", nullable = false)
    private Long gmailMailboxId;

    @Column(name = "display_name", nullable = false, length = 255)
    private String displayName;
    
    @Column(name = "full_name", nullable = false, length = 1024)
    private String fullName;

    @Column(name = "gmail_label_id", length = 128)
    private String gmailLabelId;

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 1024)
    @Column(name = "reference_embedding")
    private float[] referenceEmbedding;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private Instant updatedAt = Instant.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
