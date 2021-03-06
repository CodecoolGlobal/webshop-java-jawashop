export let template = {
    forPage: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div id="formErrors"></div>
                <div class="row p-3">
                    <form class="mx-auto">
                        <h3>Checkout</h3>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputName">Name</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. John Doe"></i>
                                <input type="text" name="name" class="form-control" id="inputName" placeholder="Your full name" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. John Doe
                                </small>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputEmail">Email</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. john@doe.com"></i>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">@</div>
                                    </div>
                                    <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email address" required>
                                </div>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. john@doe.com
                                </small>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputPhone">Phone number</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. +36/30 123 4567"></i>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="fas fa-phone"></i>
                                        </div>
                                    </div>
                                    <input type="text" name="phoneNumber" class="form-control" id="inputPhone" placeholder="+36/30 123 4523" required>
                                </div>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. +36/30 123 4567
                                </small>
                            </div>
                        </div>
                        <div class="row d-none">
                            <div class="col-12">
                                <h3>Saved addresses</h3>
                            </div>
                            <div class="col-12">
                                <ul id="addressesContainer"></ul>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="p-1">Billing address</label>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label class="sr-only" for="inputBillingCountry">Country</label>
                                <input type="text" name="billingCountry" class="form-control" id="inputBillingCountry" placeholder="Country" required>
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. Hungary"></i>
                                    <span class="d-inline d-sm-none">e.g. Hungary</span>
                                </small>
                            </div>
                            <div class="form-group col-md-3">
                                <label class="sr-only" for="inputBillingCity">City</label>
                                <input type="text" name="billingCity" class="form-control" id="inputBillingCity" placeholder="City" required>
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. Budapest"></i>
                                    <span class="d-inline d-sm-none">e.g. Budapest</span>
                                </small>
                            </div>
                            <div class="form-group col-md-2">
                                <label class="sr-only" for="inputBillingZip">Zip</label>
                                <input type="text" name="billingZip" class="form-control" id="inputBillingZip" placeholder="Zip code" required>
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 1065"></i>
                                    <span class="d-inline d-sm-none">e.g. 1065</span>
                                </small>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="sr-only" for="inputBillingAddress">Address</label>
                                <input type="text" name="billingAddress" class="form-control" id="inputBillingAddress" placeholder="Address" required>
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 44 Nagymező str."></i>
                                    <span class="d-inline d-sm-none">e.g. 44 Nagymező str.</span>
                                </small>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="p-1">Shipping address</label>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label class="sr-only" for="inputShippingCountry">Country</label>
                                <input type="text" name="shippingCountry" class="form-control" id="inputShippingCountry" placeholder="Country">
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. Hungary"></i>
                                    <span class="d-inline d-sm-none">e.g. Hungary</span>
                                </small>
                            </div>
                            <div class="form-group col-md-3">
                                <label class="sr-only" for="inputShippingCity">City</label>
                                <input type="text" name="shippingCity" class="form-control" id="inputShippingCity" placeholder="City">
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. Budapest"></i>
                                    <span class="d-inline d-sm-none">e.g. Budapest</span>
                                </small>
                            </div>
                            <div class="form-group col-md-2">
                                <label class="sr-only" for="inputShippingZip">Zip</label>
                                <input type="text" name="shippingZip" class="form-control" id="inputShippingZip" placeholder="Zip code">
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 1065"></i>
                                    <span class="d-inline d-sm-none">e.g. 1065</span>
                                </small>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="sr-only" for="inputShippingAddress">Address</label>
                                <input type="text" name="shippingAddress" class="form-control" id="inputShippingAddress" placeholder="Address">
                                <small class="form-text text-muted">
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 44 Nagymező str."></i>
                                    <span class="d-inline d-sm-none">e.g. 44 Nagymező str.</span>
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
    },

    forAddress: function(address) {
        return `
            <li>
                ${address.zip_code} ${address.address}, ${address.city} ${address.country}: 
                set as <span class="text-info address-btn">billing address</span> | 
                set as <span class="text-info address-btn">shipping address</span>.  
            </div>
        `
    },
};
