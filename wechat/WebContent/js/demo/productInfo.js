//angularJS的新版本好像都需要使用app这个东西。。。
var myApp = angular.module('weChat',[]);

myApp.controller('productInfoController', ['$scope','$http', function($scope,$http) {
	$scope.productDTO = {};
	$scope.userDTO = {};
	$scope.saveProductInfo = function(){
		alert("saveProductInfo");
		$http({
			method: 'post',
			url: "saveProductInfo.do",
			dataType:"json",
            contentType:"application/json",
            data:{productDTO:$scope.productDTO,userDTO:$scope.userDTO}
		}).success(function(data, status) {
			$scope.result = data;
		});
	};
	
	$scope.publishTaskLabel = function(){
		$scope.publishTaskFlag = true;
		$scope.showPublishedTaskFlag = false;
	};
	$scope.showPublishedTaskLabel = function(){
		$http({
			method: 'post',
			url: "getPublishedTask.do",
			dataType:"json",
            contentType:"application/json",
            data:""
		}).success(function(data, status) {
			$scope.result = data;
		});
		$scope.publishTaskFlag = false;
		$scope.showPublishedTaskFlag = true;
	};
}]);
