window.ch_bfh_red_app_util_GMap =
    function() {
        // Create the component
        var gMap = new gmap.GMap(this.getElement());
        
        // Handle changes from the server-side
        this.onStateChange = function() {
            console.log('state change ->', this.getState().value);
            gMap.setValue(this.getState().value);
        };
    
        // Pass user interaction to the server-side
        var connector = this;
        gMap.click = function() {
            connector.onClick(gMap.getValue());
        };
    };