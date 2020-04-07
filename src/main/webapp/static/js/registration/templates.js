export let template = {
    forRegistrationForm: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div id="formErrors"></div>
                <div class="row">
                    <form class="mx-auto">
                        <div class="form">
                            <div class="form-group">
                                <label for="inputName">Name</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="e.g. John Doe"></i>
                                <input type="text" name="name" class="form-control" id="inputName" placeholder="Your full name" required>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    e.g. John Doe
                                </small>
                            </div>
                            <div class="form-group">
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
                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <i class="fas fa-question-circle d-sm-inline d-none" data-toggle="tooltip" data-placement="top" title="At least 8 characters long"></i>
                                <div class="input-group">
                                    <input type="password" name="password" id="inputPassword" data-toggle="password" class="form-control" placeholder="Password" minlength="8" required>
                                </div>
                                <small class="form-text text-muted d-inline d-sm-none">
                                    At least 8 characters long
                                </small>
                            </div>
                            <div class="form-row">
                                <button class="btn btn-primary mx-auto">Register</button>
                            </div>
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

    forSuccessfullRegistration: function() {
        return `
            <div class="card-container m-3 p-3 rounded">
                <div class="alert alert-success m-0 text-center" role="alert">Successful registration!</div>
            </div>
        `
    }
};
