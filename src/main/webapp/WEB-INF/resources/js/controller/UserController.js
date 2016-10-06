angular
    .module('davosERP')
    .controller(
        'UserController', [
            '$scope',
            'UserService',
            'growlService',
            function($scope, UserService, growlService) {
                var _self = this;
                _self.user = {
                    id: null,
                    userName: '',
                    userPassword: 'Pass123#d'
                };

                _self.users = [];

                _self.submit = submit;
                _self.reset = reset;

                function submit() {
                    if (_self.user.id === null) {
                        createUser(_self.user);
                    } else {
                        growlService.growl('Already data is there',
                            'inverse')
                    }
                    reset();
                    angular.element('[id="userName"]').focus();
                }

                function reset() {
                    _self.user = {
                        id: null,
                        userName: '',
                        userPassword: 'Pass123#d'
                    };
                    // Reset Form
                    $scope.myForm.$setPristine();
                }

                function createUser(user) {
                    UserService
                        .createUser(user)
                        .then(
                            function(successResponse) {
                                if (successResponse === 'SUCCESS') {
                                    growlService
                                        .growl(
                                            'User details successfully saved',
                                            'inverse');
                                } else if (successResponse === 'ALREADYEXISTS') {
                                    growlService
                                        .growl(
                                            'User details already exists',
                                            'inverse');
                                } else if (successResponse === 'FAILURE') {
                                    growlService
                                        .growl(
                                            'User details failed to save',
                                            'inverse');
                                } else {
                                    growlService
                                        .growl(
                                            'Some untracable problem.Please contact IT dept',
                                            'inverse');
                                }
                            },
                            function(errorResponse) {
                                growlService
                                    .growl(
                                        'Some untracable problem.Please contact IT dept',
                                        'inverse');
                                console.log(errorResponse);
                            });
                }

                $scope.submitForm = function(form) {
                    if (form.validate()) {
                        submit();
                    }
                };

                $scope.validateOptions = {
                    rules: {
                        userName: {
                            required: true,
                            remote: {
                                url: 'user/findByUserName'
                            }
                        },
                        userPassword: {
                            required: true,
                            minlength: 8,
                            maxlength: 20,
                            remote: {
                                url: 'user/validatePassword'
                            }
                        }
                    },
                    messages: {
                        userName: {
                            required: 'Please enter username',
                            remote: 'Username already exists'
                        },
                        userPassword: {
                            required: 'Please enter password',
                            minlength: 'Please enter minimum {0} characters',
                            maxlength: 'Please enter below {0} characters',
                            remote: 'Password doesn\'t meet the conditions'
                        }
                    }
                };
            }
        ]);