export let template = {
    forPage: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div id="formErrors"></div>
                <div class="row p-3">
                    <form class="mx-auto">
                        <div class="form-row">
                            <div class="form-group col">
                                <label for="inputCardNumber">Card Number</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 1234 1234 1234 1234"></i>
                                <input type="text" name="card_number" class="form-control" id="inputCardNumber" placeholder="e.g. 1234 1234 1234 1234" minlength="12" maxlength="19" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. 1234 1234 1234 1234
                                </small>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col">
                                <label for="inputCardOwner">Card Owner Name</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. John Doe"></i>
                                <input type="text" name="card_owner" class="form-control" id="inputCardOwner" placeholder="e.g. John Doe" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. John Doe
                                </small>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputCardExpiringYear">Card Expiring</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 01/20 for 2020 Januar"></i>
                                <input type="text" name="card_expire" class="form-control" id="inputCardExpiringYear" placeholder="MM/YY" minlength="5" maxlength="5" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. 01/20 for 2020 Januar
                                </small>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputCardCode">Card Code</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 123"></i>
                                <input type="number" name="card_code" class="form-control" id="inputCardCode" placeholder="CVC/CVV Code" min="100" max=999" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. 123
                                </small>
                            </div>
                        </div>
                        <div class="form-row">
                            <button class="btn btn-primary mx-auto">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        `
    },

    forErrorMessage: function(message) {
        return `
            <div class="alert alert-danger text-center">${message}</div>
        `
    }
};
