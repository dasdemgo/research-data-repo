package at.ac.tuwien.querystore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "query_store")
public class Query implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2135318549456223860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;

	@Column(name = "exec_timestamp")
	private Date execTimestamp;

	@Column(name = "query")
	private String query;

	@Column(name = "query_hash")
	private String queryHash;

	@Column(name = "resultset_hash")
	private String resultsetHash;

}
