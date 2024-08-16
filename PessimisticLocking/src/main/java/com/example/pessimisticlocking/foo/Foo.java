package com.example.pessimisticlocking.foo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Foo {
    @Id
    private int id;

    private String text;

    @Override
    public String toString() {
        return "Foo [id=" + id + ", text=" + text + "]";
    }
}
