import {useEffect, useMemo} from "react";

export const useSaveToLocalStorage = (key, value, shouldSave = ()=> true) => {
    const serializedValue = useMemo(() => JSON.stringify(value), [value]);
    useEffect(() => {
        if (!shouldSave()) {
            return;
        }
        if (localStorage.getItem(key) === serializedValue) {
            return;
        }
        try {
            localStorage.setItem(key, serializedValue);
        } catch (e) {
            console.error(`Error saving ${key} to local storage`, e);
        }
    }, [key, serializedValue]);
}