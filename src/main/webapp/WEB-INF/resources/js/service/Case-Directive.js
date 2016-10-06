davosERP.directive('capitalize', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, modelCtrl) {
			var capitalize = function(inputValue) {
				if (inputValue == undefined)
					inputValue = '';
				var capitalized = inputValue.toUpperCase();
				if (capitalized !== inputValue) {
					modelCtrl.$setViewValue(capitalized);
					modelCtrl.$render();
				}
				return capitalized;
			}
			modelCtrl.$parsers.push(capitalize);
			capitalize(scope[attrs.ngModel]); // capitalize initial value
		}
	};
}).directive('lowerize', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, modelCtrl) {
			var lowerize = function(inputValue) {
				if (inputValue == undefined)
					inputValue = '';
				var lowerized = inputValue.toLowerCase();
				if (lowerized !== inputValue) {
					modelCtrl.$setViewValue(lowerized);
					modelCtrl.$render();
				}
				return lowerized;
			}
			modelCtrl.$parsers.push(lowerize);
			lowerize(scope[attrs.ngModel]); // capitalize initial value
		}
	};
})