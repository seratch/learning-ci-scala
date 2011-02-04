package learningci.chapter06

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class FisherClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.6.2 : get fisher probability " should "return expected values" in {
    val classifier = new FisherClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getTagProbabilityForWord(Word("quick"), Tag.Good) should equal(0.5714285714285715D)
    classifier.getFisherProbabily(Document("quick rabbit"), Tag.Good) should equal(0.78013986588958D)
    classifier.getFisherProbabily(Document("quick rabbit"), Tag.Bad) should equal(0.3563359628333526D)
  }


  "Chapter 6.6.3 : get classified tag " should "return good,bad,good,good" in {
    val classifier = new FisherClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick rabbit")) should equal(Tag.Good)
    classifier.getClassifiedTag(Document("quick money")) should equal(Tag.Bad)
  }

}