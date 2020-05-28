package com.dhcircle.dhCircleWebApp.model.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sub_id")
    private long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String sub_type;
	private int duration;
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_id", nullable = false)
    private SubscriptionType subscription;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.REMOVE)
	@JoinColumn(name = "pay_id", nullable = false)
    private Payment payment;
}
