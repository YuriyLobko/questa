databaseChangeLog = {

    changeSet(author: "Admin (generated)", id: "1384174845523-1") {
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

            column(name: "description", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(150)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-2") {
        createTable(tableName: "question_tag") {
            column(name: "question_tags_id", type: "bigint")

            column(name: "tag_id", type: "bigint")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-3") {
        createTable(tableName: "tag") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tagPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-7") {
        createIndex(indexName: "FKBA823BE61E54CA38", tableName: "question") {
            column(name: "author_id")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-8") {
        createIndex(indexName: "title_uniq_1384174845434", tableName: "question", unique: "true") {
            column(name: "title")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-9") {
        createIndex(indexName: "FKF5C2C7C172B43F6D", tableName: "question_tag") {
            column(name: "tag_id")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-10") {
        createIndex(indexName: "FKF5C2C7C1F1D2477B", tableName: "question_tag") {
            column(name: "question_tags_id")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-11") {
        createIndex(indexName: "name_uniq_1384174845441", tableName: "tag", unique: "true") {
            column(name: "name")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-4") {
        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "question", constraintName: "FKBA823BE61E54CA38", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-5") {
        addForeignKeyConstraint(baseColumnNames: "question_tags_id", baseTableName: "question_tag", constraintName: "FKF5C2C7C1F1D2477B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "question", referencesUniqueColumn: "false")
    }

    changeSet(author: "Admin (generated)", id: "1384174845523-6") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "question_tag", constraintName: "FKF5C2C7C172B43F6D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag", referencesUniqueColumn: "false")
    }
}
