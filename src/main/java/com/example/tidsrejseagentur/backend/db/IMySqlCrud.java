public interface ICrud {
	/**
	 * Creates a row in the database
	 * 
	 * Returns number of rows
	 */
	T create<T>(String sql, Object? params = null);
	/**
	 * Returns a single item
	 */
	T read<T>(String sql, Object? params = null);
	/**
	 * Returns all rows as a type in a list
	 */
	List<T> readAll<T>(String sql, Object? params = null);
	/**
	 * Update a row in the database
	 */
	int update(String sql, Object? params = null);
	/**
	 * Returns number of rows affected. Should for the most part just be 1 or 0.
	 */
	int delete(String sql, Object? params = null);
}