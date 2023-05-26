package Proyecto.ComandApp.errorDto;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {


    private String name;
    private String value;


    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ErrorDto)) {
            return false;
        }
        ErrorDto castOther = (ErrorDto) other;
        return new EqualsBuilder().append(name, castOther.name).append(value, castOther.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("value", value).toString();
    }

}
