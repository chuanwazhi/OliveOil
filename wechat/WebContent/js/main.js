/*//angularJS的新版本好像都需要使用app这个东西。。。
var myApp = angular.module('weChat',[]);

myApp.controller('testController', ['$scope','$http', function($scope,$http) {
	$scope.publishTaskFlag = true;
	$scope.showPublishedTaskFlag = false;
	$scope.objectList=[{
				objectName:"",
				objectCount:"1",
				objectInvoice:'Y'
		}];
	$scope.isOrNo=[
			{code:'Y',desc:'是'},
			{code:'N',desc:'否'}
	];
	$scope.publishTask = function(){
		$http({
			method: 'post',
			url: "/docc/publishTask.do",
			dataType:"json",
            contentType:"application/json",
            data:{aa:"12333",ppp:"333"}
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
*/