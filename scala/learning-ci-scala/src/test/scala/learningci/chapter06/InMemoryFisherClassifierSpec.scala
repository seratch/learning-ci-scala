package learningci.chapter06

import learningci.chapter06.input._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class InMemoryFisherClassifierSpec extends FlatSpec with ShouldMatchers {

  "Chapter 6.6.2 : get fisher probability " should "return expected values" in {
    val classifier = new InMemoryFisherClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getTagProbabilityForWord(Word("quick"), Tag.Good) should equal(0.5714285714285715D)
    classifier.getFisherProbability(Document("quick rabbit"), Tag.Good) should equal(0.78013986588958D)
    classifier.getFisherProbability(Document("quick rabbit"), Tag.Bad) should equal(0.3563359628333526D)
  }

  "Chapter 6.6.3 : get classified tag " should "return good,bad,good,good" in {
    val classifier = new InMemoryFisherClassifier
    Documents.all foreach {
      case (document, tag) => classifier.train(document, tag)
    }
    classifier.getClassifiedTag(Document("quick rabbit")) should equal(Tag.Good)
    classifier.getClassifiedTag(Document("quick money")) should equal(Tag.Bad)
    classifier.setMinimumValue(Tag.Bad, 0.8D)
    classifier.getClassifiedTag(Document("quick money")) should equal(Tag.Good)
    classifier.setMinimumValue(Tag.Good, 0.4D)
    classifier.getClassifiedTag(Document("quick money")) should equal(Tag.Good)
  }

}