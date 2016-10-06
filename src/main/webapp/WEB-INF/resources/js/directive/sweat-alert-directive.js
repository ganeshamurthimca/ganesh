/**
 * 
 */
angular.module('davosERP').directive('swalText', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var title = scope.$eval(attrs.swalTitle);
			var message = scope.$eval(atts.swalMessage)
			element.click(function() {
				swal(title, message)
			});
		}
	}
})