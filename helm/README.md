


## How to manually deploy the chart into dev
```bash
# Install the chart into development namespace
cd ./helm
helm install alert-capture  ./alert-manager --namespace development -f ./alert-manager/values.yaml
```

