package learningci.chapter06

import learningci.chapter06.datastore._

class InMemoryBasicClassifier extends AbstractClassifier with InMemoryDatastore

class SqliteBasicClassifier extends AbstractClassifier with SqliteDatastore