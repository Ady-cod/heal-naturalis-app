import { useEffect, useRef } from 'react';

export const useBootstrapCollapse = (navbarRef) => {

    // This useRef is used to hold a reference to the Bootstrap Collapse instance
    // so that we can pass it to the onClick handler of the dropdown items
    const bsCollapseRef = useRef(null);

    useEffect(() => {
        if (navbarRef.current) {

            // Initialize a new Bootstrap Collapse instance with the reference to the navbar element
            // This is necessary for collapsing the navbar programmatically when a dropdown item is clicked
            bsCollapseRef.current = new bootstrap.Collapse(navbarRef.current, {
                toggle: false, // The 'toggle' option is set to false to override the default behavior
            });

            // Cleanup function for the useEffect
            return () => {
                if (bsCollapseRef.current) {

                    // Dispose of the Bootstrap Collapse instance to remove
                    // event listeners and data attributes, freeing up resources.
                    bsCollapseRef.current.dispose();
                }
            };
        }
    }, [navbarRef]);

    // Collapse the navbar. This will be used in the onClick handler of the dropdown items
    const collapseNavbar = () => {
        bsCollapseRef.current?.hide();
    };

    return { collapseNavbar };

};