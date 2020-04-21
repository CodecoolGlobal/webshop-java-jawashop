export let template = {
    forPaymentMethodSelector: function(order) {
        return `
            <div class="card-container m-3 p-3 rounded">
                <h3>Payment methods</h3>
                <p>Your order total price: <span class="text-success font-weight-bold">${order.total_price} JPY</span></p>
                <p>Please select one of the available methods for payment:</p>
                <ul>
                    <li id="creditCardBtn"><i class="fa fa-credit-card mr-3" aria-hidden="true"></i>Credit Card</li>
                    <li id="paypalBtn"><del><i class="fab fa-cc-paypal mr-3" aria-hidden="true"></i>PayPal</del></li>
                </ul>
            </div>
        `
    },

    forCreditCardForm: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div id="formErrors"></div>
                <div class="row p-3">
                    <div class="mx-auto">
                        <form class="mx-auto">
                            <h3 class="mb-3">Credit Card Payment</h3>
                            <input type="hidden" name="type" value="credit-card">
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
                                    <label for="inputCardExpiringYear">Card Expiring Date</label>
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 01/20 for 2020 Januar"></i>
                                    <input type="text" name="card_expire" class="form-control" id="inputCardExpiringYear" placeholder="MM/YY" minlength="5" maxlength="5" required>
                                    <small class="form-text text-muted d-inline d-sm-none">
                                        e.g. 01/20 for 2020 Januar
                                    </small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputCardCode">Card Code</label>
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. 123"></i>
                                    <input type="number" name="card_code" class="form-control" id="inputCardCode" placeholder="CVC/CVV Code" min="100" max="999" required>
                                    <small class="form-text text-muted d-inline d-sm-none">
                                        e.g. 123
                                    </small>
                                </div>
                            </div>
                            <div class="form-row mb-3">
                                <div class="mx-auto">
                                    <button class="btn btn-primary">Send</button>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="mx-auto">
                                <p class="text-center">or switch to</p>
                                <button id="paypalBtn" class="btn btn-secondary">PayPal</button>
                                <p class="text-center">payment.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `
    },

    forPayPalForm: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div id="formErrors"></div>
                <div class="row">
                    <form class="mx-auto">
                        <fieldset disabled>
                            <div class="form">
                                <h3 class="mb-3">PayPal</h3>
                                <div class="alert alert-danger" role="alert">
                                    Payment with PayPal not supported yet.
                                </div>
                                <input type="hidden" name="type" value="paypal">
                                <div class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. john@doe.com"></i>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">@</div>
                                        </div>
                                        <input type="email" name="email" class="form-control" id="inputEmail" autocomplete="on" placeholder="Email address" required>
                                    </div>
                                    <small class="form-text text-muted d-inline d-sm-none">
                                        e.g. john@doe.com
                                    </small>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword">Password</label>
                                    <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="At least 8 characters long"></i>
                                    <div class="input-group">
                                        <input type="password" name="password" id="inputPassword" autocomplete="off" data-toggle="password" class="form-control" placeholder="Password" minlength="8" required>
                                    </div>
                                    <small class="form-text text-muted d-inline d-sm-none">
                                        At least 8 characters long
                                    </small>
                                </div>
                                <div class="form-row mb-3">
                                    <div class="mx-auto">
                                        <button class="btn btn-primary">Authenticate</button>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="row">
                    <div class="mx-auto">
                        <p class="text-center">or switch to</p>
                        <button id="creditCardBtn" class="btn btn-secondary">Credit Card</button>
                        <p class="text-center">payment.</p>
                    </div>
                </div>
                </div>
            </div>
        `
    },

    forErrorMessage: function(message) {
        return `
            <div class="alert alert-danger text-center">${message}</div>
        `
    },

    forSuccessfulOrder: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div class="row">
                    <h3><i class="far fa-check-circle"></i> Successful order!</h3>
                    <p>Your order </p>
                    <h4>Ordered products:</h4>
                    <div id="ordered-products"></div>
                </div>
            </div>
        `
    },

    forProduct: function(product) {
        return `
            <div class="row">
                <div class="col-4">
                    <img src="/static/img/uploads/${product.id}.jpg" class="product-image" alt="${product.name}">
                </div>
            </div>
        `
    },

    forFailedOrder: function(message) {
        return `
            <div class="card-container m-3 p-3 rounded">
            </div>
        `
    },
};
