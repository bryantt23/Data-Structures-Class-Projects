
public class Vertex {
  final private String id;
  final private String airport;
  final private String airportCode;  
  
  public Vertex(String id, String airport, String airportCode) {
    this.id = id;
    this.airport = airport;
    this.airportCode = airportCode;
  }
  public String getId() {
    return id;
  }

  public String getAirport() {
    return airport;
  }
  
  public String getAirportCode() {
	    return airportCode;
	  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vertex other = (Vertex) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return airport;
  }  
} 