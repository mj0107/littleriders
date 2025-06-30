import { BoardInfo, DropInfo } from '@types'
import { create } from 'zustand'

type realTimeInfoValue = (BoardInfo | DropInfo) & {
  status: 'BOARD' | 'DROP'
}

interface realTimeState {
  realTimeInfo: Map<string, realTimeInfoValue[]>
}

interface realTimeAction {
  addRealTimeInfo: (info: realTimeInfoValue) => void
  getRealTimeInfo: (key: string) => realTimeInfoValue[] | undefined
  clearRealTimeInfo: () => void
  removeRealTimeInfo: (key: string) => void
}

export const useRealTimeStore = create<realTimeState & realTimeAction>(
  (set, get) => ({
    realTimeInfo: new Map<string, realTimeInfoValue[]>(),

    addRealTimeInfo: (info: realTimeInfoValue) => {
      const { latitude, longitude } = info
      const key = `${latitude}-${longitude}`

      const currentMap = get().realTimeInfo
      const existedInfo = currentMap.get(key) || []

      const isExisted = existedInfo.some(
        (existedInfo) => JSON.stringify(existedInfo) === JSON.stringify(info),
      )

      if (isExisted) {
        return
      }

      set((state) => {
        const newMap = new Map(state.realTimeInfo)
        const currentInfoList = newMap.get(key) || []
        newMap.set(key, [...currentInfoList, info])

        return {
          realTimeInfo: newMap,
        }
      })
    },

    getRealTimeInfo: (key: string) => {
      return get().realTimeInfo.get(key)
    },

    clearRealTimeInfo: () => {
      set(() => ({
        realTimeInfo: new Map<string, realTimeInfoValue[]>(),
      }))
    },

    removeRealTimeInfo: (key: string) => {
      set((state) => {
        const newMap = new Map(state.realTimeInfo)
        newMap.delete(key)

        return {
          realTimeInfo: newMap,
        }
      })
    },
  }),
)
