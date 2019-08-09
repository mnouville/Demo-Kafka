package fr.excilys.compteapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageClientKafka {
    private String function;
    private Long idClient;
    private String content;
}
