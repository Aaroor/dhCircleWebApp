package com.dhcircle.dhCircleWebApp.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "payment_type")
public class PaymentType extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private long id;
	@OneToMany(mappedBy="paymentType",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private Set<Payment> lPayment;
}
