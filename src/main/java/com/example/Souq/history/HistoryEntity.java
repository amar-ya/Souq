package com.example.Souq.history;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "history")
@Data
public class HistoryEntity
{
    @Id
    private int id;
    private int sellerId;
    private int buyerId;
    private String details;
    private LocalDateTime purchaseTime;
}
