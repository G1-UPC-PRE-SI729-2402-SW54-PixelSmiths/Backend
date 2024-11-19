package com.acme.takemycar.iam.infrastructure.hashing.bcrypt;

import com.acme.takemycar.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
