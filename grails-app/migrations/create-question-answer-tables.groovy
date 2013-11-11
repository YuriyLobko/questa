databaseChangeLog = {

	changeSet(author: "Admin (generated)", id: "1384180211165-1") {
		createTable(tableName: "answer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "answerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "author_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "question_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-2") {
		createTable(tableName: "question") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "questionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "author_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "varchar(150)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-3") {
		createTable(tableName: "question_tag") {
			column(name: "question_tags_id", type: "bigint")

			column(name: "tag_id", type: "bigint")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-9") {
		createIndex(indexName: "FKABCA3FBE1E54CA38", tableName: "answer") {
			column(name: "author_id")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-10") {
		createIndex(indexName: "FKABCA3FBEE76F73E7", tableName: "answer") {
			column(name: "question_id")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-11") {
		createIndex(indexName: "FKBA823BE61E54CA38", tableName: "question") {
			column(name: "author_id")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-12") {
		createIndex(indexName: "title_uniq_1384180211066", tableName: "question", unique: "true") {
			column(name: "title")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-13") {
		createIndex(indexName: "FKF5C2C7C172B43F6D", tableName: "question_tag") {
			column(name: "tag_id")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-14") {
		createIndex(indexName: "FKF5C2C7C1F1D2477B", tableName: "question_tag") {
			column(name: "question_tags_id")
		}
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-4") {
		addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "answer", constraintName: "FKABCA3FBE1E54CA38", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-5") {
		addForeignKeyConstraint(baseColumnNames: "question_id", baseTableName: "answer", constraintName: "FKABCA3FBEE76F73E7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "question", referencesUniqueColumn: "false")
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-6") {
		addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "question", constraintName: "FKBA823BE61E54CA38", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-7") {
		addForeignKeyConstraint(baseColumnNames: "question_tags_id", baseTableName: "question_tag", constraintName: "FKF5C2C7C1F1D2477B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "question", referencesUniqueColumn: "false")
	}

	changeSet(author: "Admin (generated)", id: "1384180211165-8") {
		addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "question_tag", constraintName: "FKF5C2C7C172B43F6D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag", referencesUniqueColumn: "false")
	}
}
