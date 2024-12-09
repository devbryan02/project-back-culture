package com.project.projectbackculture.web.request;

import lombok.Builder;

@Builder
public record EmailRequest(
        String toUser,
        String subject,
        String message
){ }
