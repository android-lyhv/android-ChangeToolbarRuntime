package com.hovanly.dut.recyclerviewlikegmail;

import lombok.Data;

/**
 * Copyright@ AsianTech.Inc
 * Created by Ly Ho V. on 19/07/2017
 */
@Data
public class MessengerGmail {
    private int id;
    private boolean isImportant;
    private String picture;
    private String form;
    private String subject;
    private String message;
    private String from;
    private String timestamp;
    private boolean isRead;
}
