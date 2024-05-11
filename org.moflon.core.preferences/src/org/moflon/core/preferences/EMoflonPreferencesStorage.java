package org.moflon.core.preferences;

import java.util.HashMap;
import java.util.Map;

/**
 * UI-independent key-value preferences storage for eMoflon
 * 
 * @author Roland Kluge - Initial implementation
 */
public class EMoflonPreferencesStorage {
	
	/**
	 * Indicates unbounded adornment size for {@link #getMaximumAdornmentSize()}
	 */
	public static final int REACHABILITY_MAX_ADORNMENT_SIZE_INFINITY = 0;

	/**
	 * Default value for {@link #getMaximumAdornmentSize()}
	 */
	public static final int DEFAULT_REACHABILITY_MAX_ADORNMENT_SIZE = REACHABILITY_MAX_ADORNMENT_SIZE_INFINITY;

	/**
	 * Default value for {@link #getValidationTimeout()}
	 */
	public static final int DEFAULT_VALIDATION_TIMEOUT_MILLIS = 30000;

	/**
	 * Default value for {@link #getReachabilityEnabled()}
	 */
	public static final boolean DEFAULT_REACHABILITIY_IS_ENABLED = true;

	private final Map<String, Object> data = new HashMap<>();

	public static final String KEY_REACHABILITY_MAX_ADORNMENT_SIZE = "org.moflon.ide.ui.preferences.ReachabilityMaxAdornmentSize";

	public static final String KEY_REACHABILITY_ENABLED = "org.moflon.ide.ui.preferences.ReachabilityEnabled";

	public static final String KEY_VALIDATION_TIMEOUT = "org.moflon.ide.ui.preferences.ValidationTimeoutMillis";

	public EMoflonPreferencesStorage() {
		data.put(KEY_REACHABILITY_ENABLED, DEFAULT_REACHABILITIY_IS_ENABLED);
		data.put(KEY_REACHABILITY_MAX_ADORNMENT_SIZE, DEFAULT_REACHABILITY_MAX_ADORNMENT_SIZE);
		data.put(KEY_VALIDATION_TIMEOUT, DEFAULT_VALIDATION_TIMEOUT_MILLIS);
	}
	
	/**
	 * Stores a key-value pair
	 * 
	 * @param key
	 *                  the key
	 * @param value
	 *                  the value
	 */
	public void put(final String key, final Object value) {
		data.put(key, value);
	}

	/**
	 * Returns the value for a given key
	 * 
	 * @param key
	 *                the key
	 * @return the value if exists, otherwise <code>null</code>
	 */
	public Object get(final String key) {
		return data.get(key);
	}

	/**
	 * Returns the {@link Integer} value for a given key
	 * 
	 * @param key
	 *                the key
	 * @return the value if exists, otherwise <code>null</code>
	 * @throws RuntimeException
	 *                              if the value exists but has the wrong type.
	 */
	public Integer getInt(final String key) {
		return getWithCast(key, Integer.class);
	}

	/**
	 * Returns the {@link Double} value for a given key
	 * 
	 * @param key
	 *                the key
	 * @return the value if exists, otherwise <code>null</code>
	 * @throws RuntimeException
	 *                              if the value exists but has the wrong type.
	 */
	public Double getDouble(final String key) {
		return getWithCast(key, Double.class);
	}

	/**
	 * Returns the {@link Boolean} value for a given key
	 * 
	 * @param key
	 *                the key
	 * @return the value if exists, otherwise <code>null</code>
	 * @throws RuntimeException
	 *                              if the value exists but has the wrong type.
	 */
	public Boolean getBoolean(final String key) {
		return getWithCast(key, Boolean.class);
	}

	/**
	 * Returns the {@link String} value for a given key
	 * 
	 * @param key
	 *                the key
	 * @return the value if exists, otherwise <code>null</code>
	 * @throws RuntimeException
	 *                              if the value exists but has the wrong type.
	 */
	public String getString(final String key) {
		return getWithCast(key, String.class);
	}

	/**
	 * Retrieves the value for the given key and casts it if necessary to the given
	 * type
	 * 
	 * @param key
	 *                 the key
	 * @param type
	 *                 the desired type
	 * @return the value if exists, otherwise <code>null</code>
	 * @throws RuntimeException
	 *                              if the value exists but has the wrong type.
	 */
	private <T> T getWithCast(final String key, final Class<T> type) {
		final Object value = get(key);
		if (value == null) {
			return null;
		} else {
			try {
				return type.cast(value);
			} catch (final ClassCastException e) {
				reportUnsuitableType(value, type);
				return null;
			}
		}
	}

	/**
	 * Throws an exception that records that the given value is not of the expected
	 * type
	 */
	private void reportUnsuitableType(final Object value, final Class<?> type) {
		throw new IllegalArgumentException(String.format("Retrieved object %s is not of type %s", value, type));
	}

}
