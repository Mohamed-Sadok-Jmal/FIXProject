'use strict';

toastr.options = {
    "debug": false,
    "positionClass": "toast-top-right",
    "onclick": null,
    "fadeIn": 300,
    "fadeOut": 1000,
    "timeOut": 3000,
    "extendedTimeOut": 1000
};

app.service('notificationService', function ($http) {
    this.displaySuccess = function(message) {
        toastr.success(message);
    }
    this.displayError = function(error) {
        if (Array.isArray(error)) {
            error.forEach(function (err) {
                toastr.error(err);
            });
        } else {
            toastr.error(error);
        }
    }
    this.displayWarning = function(message) {
        toastr.warning(message);
    }
    this.displayInfo = function(message) {
        toastr.info(message);
    }
});