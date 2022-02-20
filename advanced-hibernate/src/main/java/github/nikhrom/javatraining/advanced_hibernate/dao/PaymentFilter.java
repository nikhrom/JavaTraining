package github.nikhrom.javatraining.advanced_hibernate.dao;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentFilter {
    String firstname;
    String lastname;
}
