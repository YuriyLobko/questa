databaseChangeLog = {

    changeSet(author: "Admin (generated)", id: "1384083589151-1") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-2") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "first_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BIT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-3") {
        createTable(tableName: "user_role") {
            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-4") {
        addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-7") {
        createIndex(indexName: "authority", tableName: "role", unique: "true") {
            column(name: "authority")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-8") {
        createIndex(indexName: "email", tableName: "user", unique: "true") {
            column(name: "email")
        }
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-5") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "questa", constraintName: "FK143BF46A18711418", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "questa", referencesUniqueColumn: "false")
    }

    changeSet(author: "Admin (generated)", id: "1384083589151-6") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "questa", constraintName: "FK143BF46ABD9BD7F8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "questa", referencesUniqueColumn: "false")
    }

    include file: 'create-tag-table.groovy'

    include file: 'create-question-answer-tables.groovy'
}
