package com.cm6123.snl.dice;

import java.util.Iterator;
import java.util.List;

public class DiceSet {
  /**
   * amount of dice rolled when using object.
   */
  private Integer count;

  /**
   * potential number rolled when rolling each dice in object.
   */
  private Integer faces;

  /**
   * List of Dice objects in the set.
   */
  private List<Dice> dice;

  /**
   * Construct a set of Dice.  Each dice has the same number of faces.
   *
   * @param newface - how many faces on each dice.
   * @param newCount - how many dice in the set.
   */
  public DiceSet(final Integer newface, final Integer newCount) {
    this(newface, newCount, new RandomDiceFactory());
  }

  /**
   * Construct a set of Dice providing a Factory to control the Dice.
   * Default factory is a RandomDiceFactory.
   * @param newFace - how many faces on each Dice.
   * @param newCount - how many dice.
   * @param factory - factory that constrains the type of Dice included
   */
  public DiceSet(final Integer newFace,
                 final Integer newCount,
                 final DiceFactory factory) {

    dice = factory.makeDice(newFace, newCount);

  }

  /**
   * Tells the set of Dice to roll.
   * @return a DiceResult object
   */
  public DiceResult roll() {

    if (dice.size() == 1) {
      return new DiceResult(dice.get(0).roll(), Boolean.FALSE);
    }

    Boolean isDouble = true;
    Integer value = 0;
    Integer lastValue = 0;

    Iterator<Dice> dices = dice.iterator();
    Dice firstDice = dices.next();
    value += firstDice.roll();
    lastValue = value;

    while (dices.hasNext()) {
      Dice aDice = dices.next();
      Integer nextValue = aDice.roll();
      isDouble = isDouble && (lastValue.equals(nextValue));
      lastValue = nextValue;
      value += nextValue;
    }

    return new DiceResult(value, isDouble);

  }

  /**
   * Getter to source stored count value.
   * @return count - number of dice used in object.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * Source face count of each die.
   * @return faces - potential value of each die.
   */
  public Integer getFaces() {
    return faces;
  }

}
