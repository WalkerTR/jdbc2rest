package dev.finiq

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class DatabaseClient {
    private val pool: HikariDataSource

    init {
        val poolConfig: HikariConfig = HikariConfig()
        poolConfig.jdbcUrl = "jdbc_url"
        poolConfig.username = "database_username"
        poolConfig.password = "database_password"
        poolConfig.addDataSourceProperty( "cachePrepStmts" , "true" )
        poolConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" )
        poolConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" )
        pool = HikariDataSource(poolConfig)
    }

    fun getDatabaseInfo(): Nothing {
        TODO()
    }

    fun getCatalogs(): List<String> {
        pool.connection.use { connection ->
            val metaData = connection.metaData
            val resultSet = metaData.getCatalogs()
            val result = arrayListOf<String>()
            while (resultSet.next()) {
                val catalogName = resultSet.getString("TABLE_CAT")
                result.add(catalogName)
            }
            return result
        }
    }

    fun getSchemas(): List<String> {
        pool.connection.use { connection ->
            val metaData = connection.metaData
            val resultSet = metaData.getSchemas()
            val result = arrayListOf<String>()
            while (resultSet.next()) {
                val schemaName = resultSet.getString("TABLE_SCHEM")
                result.add(schemaName)
            }
            return result
        }
    }

    fun getTables(catalog: String? = null, schema: String? = null): List<String> {
        pool.connection.use { connection ->
            val metaData = connection.metaData
            val resultSet = metaData.getTables(catalog, schema, "%", null)
            val result = arrayListOf<String>()
            while (resultSet.next()) {
                val tableName = resultSet.getString("TABLE_NAME")
                result.add(tableName)
            }
            return result
        }
    }

    fun getTableColumns(table: String, catalog: String? = null, schema: String? = null): List<String> {
        pool.connection.use { connection ->
            val metaData = connection.metaData
            val resultSet = metaData.getColumns(catalog, schema, table, "%")
            val result = arrayListOf<String>()
            while (resultSet.next()) {
                val columnName = resultSet.getString("COLUMN_NAME")
                result.add(columnName)
            }
            return result
        }
    }

    fun select(catalog: String, schema: String, table: String) {
        pool.connection.use { connection ->
            TODO()
        }
    }

    fun createTable(dbName: String, tableName: String) {
        TODO()
    }
}