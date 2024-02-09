/**
 * An enum representing the three states of the quiz list:
 * - ALL (all questions)
 * - CORRECT (only correctly answered questions)
 * - INCORRECT (only incorrectly answered questions)
 */
public enum ListingMode {
  /**
   * ALL stands for listing ALL the MultipleChoiceQuestions in the quiz6
   */
  ALL, 
  /**
   * CORRECT stands for listing the correct MultipleChoiceQuestions only (answered correctly)
   */
  CORRECT, 
  /**
   * INCORRECT stands for listing the incorrect MultipleChoiceQuestions only (answered incorrectly)
   */
  INCORRECT;
}