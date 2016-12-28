<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="boxes" class="row">
        <div id="dialog" class="window">
            <span class="text-info">Passer un ordre</span>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form ng-form-commit class="form-control-static" name="newOrderForm" ng-submit="newOrderForm.$valid" novalidate>
                        <div class="row form-group">
                            <label for="inputValue" class="col-sm-5 control-label">Valeur</label>
                            <select class="col-sm-7 form-control" id="inputValue" name="inputValue"
                                    ng-model="order.idValue"
                                    ng-options="quotation.id as quotation.name for quotation in quotations"
                                    required>
                        	</select>
                        </div>
                        <div class="row form-group">
                            <label for="inputNbActions" class="col-sm-5 control-label">Nombre d'actions</label>
                            <input type="text" class="col-sm-7 form-control" id="inputNbActions" name="inputNbActions" ng-model="order.qte" ng-change="checkQte(cityToAdd.Label)" required />
                        </div>
                        <div class="row form-group">
                            <label for="inputType" class="col-sm-5 control-label">Type</label>
                            <select class="col-sm-7 form-control" id="inputType" name="inputType"
                                    ng-model="order.type"
                                    ng-options="o.v as o.n for o in [{ n: 'Au marché', v: 'Market' }, { n: 'Limité', v: 'Limit' }]"
                                    ng-selected="order.type">
                        	</select>
                        </div>
                        <input type="submit" class="btn btn-info" value="OK" ng-disabled="!newOrderForm.$valid" />
                        <button class="btn btn-info close-popup" ng-click="clearForm()">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
        <div ng-click="clearForm()" style="width: 1478px; font-size: 32pt; color:white; height: 602px; display: none; opacity: 0.8;" id="mask"></div>
    </div>