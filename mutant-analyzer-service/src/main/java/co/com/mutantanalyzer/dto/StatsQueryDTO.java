
package co.com.mutantanalyzer.dto;

/**
 * Descripción DTO para los mutantes
 * 
 * @author felipe.lopera
 *
 */
public class StatsQueryDTO {

	private Long count;
	private String description;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
