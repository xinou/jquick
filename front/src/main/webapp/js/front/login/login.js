var loginApp = angular.module("loginApp", []);
loginApp.controller("loginCtrl", function($scope, $http) {
	// 用户登录
	$scope.login = function() {
		var user = $scope.user;
		// md5加密用户密码
		var userPwd = $.md5(user.userPwd);
		user.userPwd = userPwd;
		// 请求后台用户登录方法
		console.log(user);
		var url = $("#path").val() + "/user/login.do";
		$http.post(url, user).success(function(data) {
			console.log(data);
		});
	},
	// 用户重置
	$scope.reset = function() {
		$scope.user = null;
	}
});