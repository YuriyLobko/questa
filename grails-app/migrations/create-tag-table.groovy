databaseChangeLog = {

	changeSet(author: "Admin (generated)", id: "1384165687283-1") {
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

	changeSet(author: "Admin (generated)", id: "1384165687283-2") {
		createIndex(indexName: "name_uniq_1384165687207", tableName: "tag", unique: "true") {
			column(name: "name")
		}
	}
}
