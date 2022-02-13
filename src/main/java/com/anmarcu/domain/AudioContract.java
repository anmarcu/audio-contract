package com.anmarcu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "audio_contracts", name = "audio_contract")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AudioContract {
    @Id
    @SequenceGenerator(name = "audio_contract_seq", schema = "audio_contracts", sequenceName = "audio_contract_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audio_contract_seq")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "contract")
    private String contract;

    @Column(name = "audioContract")
    private String audioContract;
}
