import axios from 'axios';

const urlPrefix = 'http://localhost:8011/deepexi-user/api/v1/components/'


// 获取组件信息
function fetchAllComponent(currentPage, pageSize, filterForm) {
  return axios.get(urlPrefix, {
    params:{
      page: currentPage,
      limit: pageSize,
      ...filterForm
    }
  })
}

// 获取一个组件信息
function fetchComponent(id) {
  return axios.get(urlPrefix + id)
}

// 批量删除组件信息
function deleteComponents(componentIdList) {
  // eslint-disable-next-line no-console
  console.log(JSON.stringify(componentIdList))
  return axios.delete('http://localhost:8011/provider/components/', {
    data: componentIdList
  })
}

// 批量删除组件信息
function deleteComponent(id) {
  // eslint-disable-next-line no-console
  return axios.delete(urlPrefix + id)
}

// 批量上架或者下架
function updateComponentStatus(componentList) {
  return axios.put('http://localhost:8011/provider/components/status', componentList)
}

// 更新单个组件信息
function updateComponent(data) {
  return axios.put(urlPrefix + data.id, data)
}

// 新增一个组件
function saveComponent(componentForm) {
  return axios.post(urlPrefix, componentForm)
}

export default {
  fetchAllComponent,
  fetchComponent,
  deleteComponents,
  deleteComponent,
  updateComponentStatus,
  updateComponent,
  saveComponent
}