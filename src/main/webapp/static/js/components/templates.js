export let template = {
    forNumberPicker: function() {
        return `
            <div class="number-picker row form-group justify-content-center">
                <button class="col-3 form-control fas fa-minus-square p-0"></button>
                <input class="col-4 form-control text-center ml-1 mr-1 p-0" readonly value="0">
                <button class="col-3 form-control fas fa-plus-square p-0"></button>
            </div>
        `
    },
}
