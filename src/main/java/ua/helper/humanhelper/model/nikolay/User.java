package ua.helper.humanhelper.model.nikolay;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@ToString
@Table(name = "game_usr")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private Long score;

  @Transient private Long place;

  public User(String username, Long score) {
    this.username = username;
    this.score = score;
  }
}
