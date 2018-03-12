/**
 * Handles how values are stored and retrieved
 * from local storage
 *
 * @since 0.1.0
 */
export class LocalStorage {

    /**
     * Gets a value stored in local storage with the key passed as
     * parameter
     *
     * @param key the key the value is stored under
     * @return the value
     * @since 0.1.0
     */
    get (key) {
        return JSON.parse(window.localStorage.getItem(key))
    }

    /**
     * Stores a given value associated with the key passed as
     * parameter
     *
     * @param key the key we want to store the value with
     * @param newValue the value to store
     * @return the stored value
     * @since 0.1.0
     */
    set (key, newValue = undefined) {
        window.localStorage.setItem(
            key,
            JSON.stringify(newValue)
        )

        return newValue
    }

    /**
     * Removes the value stored under the key passed as parameter
     *
     * @param key the key used to store the value
     * @return true if the value was removed successfully
     * @since 0.1.0
     */
    remove (key) {
        window.localStorage.removeItem(key)

        return true
    }

    /**
     * Empties the local storage
     *
     * @return true if the cleaning up was successful
     * @since 0.1.0
     */
    clear () {
        window.localStorage.clear()

        return true
    }
}
