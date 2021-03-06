export let ui = {
    __header: null,
    __loadedScriptCounter: 0,
    __callback: null,

    render: function(callback) {
        ui.__header = document.querySelector("head");
        ui.__callback = callback;
        ui.__header.innerHTML +=
            '<meta charset="utf-8" />' +
            '<meta name="author" content="Codecool" />' +
            '<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />' +
            '<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">' +
            '<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">' +
            '<link href="https://fonts.googleapis.com/css?family=Barlow+Condensed|Exo+2|Monoton|Orbitron|Play&display=swap" rel="stylesheet"><script src="/static/js/core.js" type="module" defer></script>' +
            '<link rel="stylesheet" type="text/css" href="/static/css/main.css" /><link rel="stylesheet" type="text/css" href="/static/css/notiflix-2.1.2.min.css" />';

        ui.__injectJavaScriptFile("https://code.jquery.com/jquery-3.3.1.min.js", "sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=")
        ui.__injectJavaScriptFile("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js", "sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49")
        ui.__injectJavaScriptFile("https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js", "sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k")
        ui.__injectJavaScriptFile("/static/js/external/notiflix-2.1.2.min.js");
        ui.__injectJavaScriptFile("https://unpkg.com/bootstrap-show-password@1.2.1/dist/bootstrap-show-password.min.js");
    },

    __injectJavaScriptFile: function(path, integrity) {
        const script = document.createElement("script");
        script.src = path;
        script.async = false;
        if (integrity !== undefined) {
            script.setAttribute("integrity", integrity);
        }
        script.setAttribute("crossorigin", "anonymous");
        script.onload = function() {
            ui.__loadedScriptCounter++;

            if (ui.__loadedScriptCounter === 5) {
                ui.__callback();
            }
        }
        ui.__header.appendChild(script);
    },

};
