'use strict';

app
    .controller('quotationCtrl', function ($scope, $http, notificationService) {
        // sorting
        $scope.orderProp = 'name';
        $scope.reverse = false;
        // pagination
        $scope.pageSize = 10;
        $scope.currentPage = 0;
        //Initialize
        $scope.quotationToBuy = {};
        //Form
        $scope.order = {};
        $scope.order.qte = 0;
        $scope.order.type = 'Market';

        $scope.sort = function (name) {
            if (name == $scope.orderProp) $scope.reverse = !$scope.reverse;
            else {
                $scope.orderProp = name;
                $scope.reverse = false;
            }
        }

        var response = $http.get('/FIXProject/quotation/');
        response.success(function (data) {
        	$scope.quotations = data;
        	$scope.numberOfPages = function () {
        		return Math.ceil($scope.quotations.length / $scope.pageSize);
        	}
        });
        response.error(function (data, status, headers, config) {
            alert("AJAX failed to get data, status=" + status);
        });

        $scope.getQuotationToBuy = function (id) {
            var response = $http.get('/FIXProject/quotation/' + id);

            response.success(function (data) {
                $scope.quotationToBuy = data;
                $scope.order.idValue = $scope.quotationToBuy.id;
            });

            response.error(function (data, status, headers, config) {
                alert("AJAX failed to get data, status=" + status);
            });
        }
    })
    .filter('startFrom', function () {
        return function (input, start) {
            start = +start; //parse to int
            return input.slice(start);
        }
    })
;