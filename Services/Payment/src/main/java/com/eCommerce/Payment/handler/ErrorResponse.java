package com.eCommerce.Payment.handler;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
