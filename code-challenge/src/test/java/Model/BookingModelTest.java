package Model;

import com.statista.code.challenge.model.Departments;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingModelTest {

    private String description;
    private double price;
    private String currency;
    private long subscriptionStartDate;
    private String email;
    private Departments department;
}
