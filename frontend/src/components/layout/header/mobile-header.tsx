import { Bell, Search } from "lucide-react"
import Link from "next/link"
import { useUserStore } from "@/store/userStore"
import { DeliveryAddressDrawer } from "@/components/delivery-address-drawer"

export function MobileHeader() {
  const { user } = useUserStore()

  return (
    <header className="w-full p-2">
      <div className="flex items-center justify-between">
        <h1 className="text-xl font-bold text-primary">BitShop</h1>
        <DeliveryAddressDrawer user={user} />
      </div>
      <div className="mt-2 flex items-center justify-between gap-2">
        <Link
          href="/search"
          className="flex items-center gap-2 bg-muted text-muted-foreground w-full rounded-lg p-2 hover:opacity-80"
        >
          <Search size={20} />
          <p>Искать на BitShop</p>
        </Link>
        <div
          role="button"
          className="p-2 bg-muted text-muted-foreground rounded-lg hover:cursor-pointer hover:opacity-80"
        >
          <Bell size={20} />
        </div>
      </div>
    </header>
  )
}
