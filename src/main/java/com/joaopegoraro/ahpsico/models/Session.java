package com.joaopegoraro.ahpsico.models;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.joaopegoraro.ahpsico.enums.SessionPaymentStatus;
import com.joaopegoraro.ahpsico.enums.SessionPaymentType;
import com.joaopegoraro.ahpsico.enums.SessionStatus;
import com.joaopegoraro.ahpsico.enums.SessionType;
import com.joaopegoraro.ahpsico.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Assignment> assignments;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int groupIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionPaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionPaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole updatedBy;

    @Column(nullable = true)
    private String updateMessage;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        if (type == null)
            type = SessionType.INDIVIDUAL;
        if (status == null)
            status = SessionStatus.NOT_CONFIRMED;
        if (paymentStatus == null)
            paymentStatus = SessionPaymentStatus.NOT_PAYED;
        if (paymentType == null)
            paymentType = SessionPaymentType.PARTICULAR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public SessionPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(SessionPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public SessionPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(SessionPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public UserRole getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserRole updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
